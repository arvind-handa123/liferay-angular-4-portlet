import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class EmployeeService {

  constructor(private http: Http) { }
  
  getEmployees() {
    return this.http.get('http://localhost:8080/o/byteparity/emp/employees')
        .toPromise()
        .then(response => {
            return response.json();
        })
        .catch(err => {
            console.log(err);
        });
  }
  
  deleteEmployee(empId : any){
     return this.http.post('http://localhost:8080/o/byteparity/emp/delete',empId)
        .toPromise()
        .then(response => {
            return response.json();
        })
        .catch(err => {
            console.log(err);
        });
  }
  
}
