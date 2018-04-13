import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-employee',
  templateUrl: "/o/angular-npm-portlet/lib/app/employee/employee.component.html",
  providers : [ EmployeeService ]
})
export class EmployeeComponent implements OnInit {
  public responceData: any = [];
  private empIdObj: {};
  form: FormGroup;
  private responseCode: number;
  constructor(private employeeService: EmployeeService, private router: Router) {}

  ngOnInit() {
    this.employeeService.getEmployees().then(response => {
      this.responceData = response.data;
    });
  }

  editEmployee(empId: number) {
    this.router.navigate(['/member', empId]);
  }

  deleteEmployee(empId: number) {
    if (confirm('Are you sure you want to delete ?')) {
      this.empIdObj = {
        "empId": empId
      };
      this.employeeService.deleteEmployee(this.empIdObj).then(response => {
        this.responseCode = response.responseCode;
        if (response.responseCode == 200) {
           this.employeeService.getEmployees().then(response => {
            this.responceData = response.data;
          });
        }
      });
    }
  }
}
