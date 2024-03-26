import {Component, OnInit} from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { NzContentComponent, NzFooterComponent, NzHeaderComponent, NzLayoutComponent, NzSiderComponent } from 'ng-zorro-antd/layout';
import { NzBreadCrumbComponent, NzBreadCrumbItemComponent } from 'ng-zorro-antd/breadcrumb';
import { NzIconDirective } from "ng-zorro-antd/icon";
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzFlexDirective } from "ng-zorro-antd/flex";
import { NzAvatarComponent } from "ng-zorro-antd/avatar";
import { DashboardComponent } from "../../pages/dashboard/dashboard.component";
import { NzTypographyComponent } from "ng-zorro-antd/typography";
import { NzSliderComponent } from "ng-zorro-antd/slider";
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [
    RouterOutlet,
    DashboardComponent,
    NzLayoutComponent,
    NzSiderComponent,
    NzHeaderComponent,
    NzContentComponent,
    NzBreadCrumbComponent,
    NzBreadCrumbItemComponent,
    NzFooterComponent,
    NzIconDirective,
    NzMenuModule,
    NzIconModule,
    RouterModule,
    NzFlexDirective,
    NzAvatarComponent,
    NzTypographyComponent,
    NzSliderComponent,
  ],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent implements OnInit{
  isCollapsed = false;
  user!: User;

  constructor(private authService: AuthService,) { }

  ngOnInit(): void {
    this.authService.getUser().subscribe({
      next: (res: any) => {
        this.user = res;
      },
      error: (err: any) => {
        console.log(err)
      }
    })
  }
}
