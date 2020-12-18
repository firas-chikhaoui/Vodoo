import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FonctionListComponent } from './fonction-list.component';

describe('FonctionListComponent', () => {
  let component: FonctionListComponent;
  let fixture: ComponentFixture<FonctionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FonctionListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FonctionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
