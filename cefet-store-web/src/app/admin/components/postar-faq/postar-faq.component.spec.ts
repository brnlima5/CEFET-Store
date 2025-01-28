import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostarFaqComponent } from './postar-faq.component';

describe('PostarFaqComponent', () => {
  let component: PostarFaqComponent;
  let fixture: ComponentFixture<PostarFaqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostarFaqComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostarFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
