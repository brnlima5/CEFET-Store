import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostarCupomComponent } from './postar-cupom.component';

describe('PostarCupomComponent', () => {
  let component: PostarCupomComponent;
  let fixture: ComponentFixture<PostarCupomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostarCupomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostarCupomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
