import { Component, DoCheck, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user.model';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit, DoCheck  {
  isLoggedIn = false;
  user!: User;

  constructor(public login: LoginService,private router: Router) {}



  ngOnInit(): void {

 
}

ngDoCheck(): void {
  // Check current route
  const currentRoute = this.router.url;

  console.log(this.router.url);

  if (currentRoute === '/login' || currentRoute === '/signup' || currentRoute === '/') {
    this.isLoggedIn = false;
} else {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubject.asObservable().subscribe((data) => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
    });
}
}




public logout() {
    this.login.logout();
    window.location.reload();
    // this.login.loginStatusSubject.next(false);
  }
}
