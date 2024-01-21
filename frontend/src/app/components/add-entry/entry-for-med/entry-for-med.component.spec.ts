import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EntryForMedComponent } from './entry-for-med.component';

describe('EntryForMedComponent', () => {
  let component: EntryForMedComponent;
  let fixture: ComponentFixture<EntryForMedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EntryForMedComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EntryForMedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
