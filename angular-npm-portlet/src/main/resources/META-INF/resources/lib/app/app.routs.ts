import { Routes } from '@angular/router';
import { MemberComponent }  from './member/member.component';
import { AppComponent } from './app.component';
import { EmployeeComponent }  from './employee/employee.component';

export const AppRoutes: Routes = [
  {
     path : 'employee',
     component : EmployeeComponent
  },{
     path : 'employee/member',
     component : MemberComponent
  },{
     path: 'member/:empId', 
     component: MemberComponent 
  }
];