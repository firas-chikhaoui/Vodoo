import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionFonctionComponent } from './gestion-fonction.component';

describe('GestionFonctionComponent', () => {
  let component: GestionFonctionComponent;
  let fixture: ComponentFixture<GestionFonctionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionFonctionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionFonctionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
