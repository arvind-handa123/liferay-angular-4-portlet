import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class MemberService {

  constructor(private http: Http) { }
  
  addEmployee(employee : any) {
    return this.http.post('http://localhost:8080/o/byteparity/emp/addOrUpdateEmp',employee)
        .toPromise()
        .then(response => {
            return response.json();
        })
        .catch(err => {
            console.log(err);
        });
  }
  
  getEmployee(empId : number) {
    return this.http.get('http://localhost:8080/o/byteparity/emp/employee/'+empId)
        .toPromise()
        .then(response => {
            return response.json();
        })
        .catch(err => {
            console.log(err);
        });
  }
  
}
