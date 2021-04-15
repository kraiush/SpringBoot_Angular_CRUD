import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Product } from "../../model/product";
import { ProductService } from "../../service/product.service";
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private productService: ProductService) { }

  // @ts-ignore
  addForm: FormGroup;

  ngOnInit() {
    this.addForm = this.formBuilder.group({
      id: ['', [Validators.required]],
      code: ['', [Validators.required]],
      name: ['', [Validators.required]],
      price: ['', [Validators.required]],
      article: ['', [Validators.required]],
      productiondate: ['', [Validators.required]],
      quantity:  ['']
    });
  }

  onSubmit() {
    // console.log(this.addForm.value);
    this.productService.createProduct(this.addForm.value)
      .subscribe( data => {
        this.router.navigate(['products']);
      });
  }

   /* Handle form errors in Angular */
  public errorHandling = (control: string, error: string) => {
    return this.addForm.controls[control].hasError(error);
  }
  /* Date */
  // @ts-ignore
  date(e) {
    let convertDate;
    convertDate = new Date(e.target.value).toISOString().substring(0, 10);
    // @ts-ignore
    this.addForm.get('productiondate').setValue(convertDate, {
      onlyself: true
    })
  }

  list(){
    this.router.navigate(['products']);
  }
}
