import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  user!: User;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.authService.getUser().subscribe({
      next: (user: User) => {
        this.user = user;
        this.checkAdminRedirect();
      },
      error: (error: any) => {
        console.error('Error retrieving user information:', error);
      }
    });
  }

  private checkAdminRedirect(): void {
    if (this.user && this.user.roles.some(role => role === 'ADMIN' || role === 'STAFF')) {
      this.router.navigate(['/dashboard']);
    }
  }


}
