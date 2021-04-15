import { Component, OnInit, Inject } from '@angular/core';
import { ProductDetailsComponent } from '../product-details/product-details.component';
import { Observable } from "rxjs";
import { Product } from "../../model/product";
import { ProductService } from "../../service/product.service";
import { Router } from '@angular/router';
@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  // @ts-ignore
  products: Observable<Product[]>;

  constructor(private productService: ProductService,
              private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.products = this.productService.getProductsList();
  }

  updateProduct(product: Product): void {
    window.localStorage.removeItem('editProductId');
    window.localStorage.setItem('editProductId', product.id.toString());
    this.router.navigate(['product-update']);
  };

  addProduct(): void {
    this.router.navigate(['create-product']);
  };

  deleteProduct(id: number) {
    this.productService.deleteProduct(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  getproductDetails(id: number){
    this.router.navigate(['product-details', id]);
  }
}
