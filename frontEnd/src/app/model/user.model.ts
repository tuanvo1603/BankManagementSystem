export interface User {
    id: number;
    firstName: string;
    lastName: string;
    username: string;
    phone: string;
    authorities: Authority[];
    enabled: boolean;
  }
  
  interface Authority {
    authority: string;
  }
  