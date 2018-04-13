import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector : "app-root", 
	templateUrl: "/o/angular-npm-portlet/lib/app/app.component.html"
})
export class AppComponent {
  
  constructor(private router : Router) { }
  
  ngOnInit() {
    this.router.navigate(['/employee']);
  }
}
