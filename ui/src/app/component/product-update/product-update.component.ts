import { Component, OnInit , Inject } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators }  from '@angular/forms';
import { first } from 'rxjs/operators';
import { Product } from "../../model/product";
import { ProductService } from "../../service/product.service";

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.css']
})
export class ProductUpdateComponent implements OnInit {

  // @ts-ignore
  product: Product;
  // @ts-ignore
  editForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private router: Router,
              private productService: ProductService) { }

  ngOnInit() {
    let productId = window.localStorage.getItem('editProductId');
    if(!productId) {
      console.log('Invalid action!');
      this.gotoList();
      return;
    }

    this.editForm = this.formBuilder.group({
      id: [''],
      code: ['', [Validators.required]],
      name: ['', [Validators.required]],
      price: ['', [Validators.required]],
      article: ['', [Validators.required]],
      productiondate: [''],
      quantity:  ['']
    });

    this.productService.getProduct(Number(productId))
      .subscribe(data => {
        this.editForm.setValue(data);
      }, error => console.log(error));
  }

  updateProduct() {
    this.productService.updateProduct(this.editForm.value)
      .pipe(first())
      .subscribe(data => console.log(data), error => console.log(error));
    this.product = new Product();
    this.gotoList();
  }
  onSubmit() {
    this.updateProduct();
  }

  gotoList() {
    this.router.navigate(['products']);
  }

  /* Handle form errors in Angular */
  public errorHandling = (control: string, error: string) => {
    return this.editForm.controls[control].hasError(error);
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
