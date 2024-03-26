import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';
import { User } from '../models/user';
import { catchError, of, switchMap } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const authService: AuthService = inject(AuthService);
  const router: Router = inject(Router);

  return authService.getUser().pipe(
    switchMap((user: User) => {
      if (!user) {
        router.navigate(['/login']);
        return of(false);
      }

      if (route.url.length > 0) {
        const path = route.url[0].path;
        if (path === 'dashboard') {
          if (user.roles.some(role => role === 'ADMIN' || role === 'STAFF')) {
            return of(true);
          } else {
            router.navigate(['/home']);
            return of(false);
          }
        } else {
          return of(true);
        }
      } else {
        return of(true);
      }
    }),
    catchError(() => {
      router.navigate(['/']);
      return of(false);
    })
  );
};
