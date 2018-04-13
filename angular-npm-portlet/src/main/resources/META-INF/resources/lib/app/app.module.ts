import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { RouterModule } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';
import { AppRoutes } from './app.routs';

import { EmployeeComponent }  from './employee/employee.component';
import { MemberComponent }  from './member/member.component';
import { AppComponent } from './app.component';

import { EmployeeService } from './employee/employee.service';
import { MemberService } from './member/member.service';


@NgModule({
	imports: [
      BrowserModule, 
      FormsModule,
      HttpModule,
      RouterModule.forRoot(AppRoutes, { useHash: true })
    ],
	declarations: [AppComponent,EmployeeComponent,MemberComponent],
	entryComponents: [AppComponent],
	bootstrap: [], 
	providers: [ {provide: APP_BASE_HREF, useValue : '/' },EmployeeService,MemberService],
})
export class AppModule {
	ngDoBootstrap() {}
}
