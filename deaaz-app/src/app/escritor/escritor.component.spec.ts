import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EscritorComponent } from './escritor.component';

describe('EscritorComponent', () => {
  let component: EscritorComponent;
  let fixture: ComponentFixture<EscritorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EscritorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EscritorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
