import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostarProdutoComponent } from './postar-produto.component';

describe('PostarProdutoComponent', () => {
  let component: PostarProdutoComponent;
  let fixture: ComponentFixture<PostarProdutoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostarProdutoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostarProdutoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
