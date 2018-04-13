import { Component, OnInit } from '@angular/core';
import { MemberService } from './member.service';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-member',
  templateUrl: "/o/angular-npm-portlet/lib/app/member/member.component.html",
  providers : [MemberService]
  
})
export class MemberComponent implements OnInit {
  private empId : number;
  public emp : any = {};
  
  constructor(private memberService : MemberService,private router : Router,private activatedRoute : ActivatedRoute ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.empId = +params['empId']; 
    });
    
    if(!isNaN(this.empId)){
      this.memberService.getEmployee(this.empId).then(response => {
        this.emp = response.data;
      });
    }
  }
  
  onSubmit(form: any): void {  
    this.memberService.addEmployee(form).then(response => {
      if(response.responseCode == 200){
          this.router.navigate(['/employee']);
       }
    });
  }
  
  cancelForm(){
    this.router.navigate(['/employee']);
  } 
}
