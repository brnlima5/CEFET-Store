import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostarCategoriaComponent } from './postar-categoria.component';

describe('PostarCategoriaComponent', () => {
  let component: PostarCategoriaComponent;
  let fixture: ComponentFixture<PostarCategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostarCategoriaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostarCategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
