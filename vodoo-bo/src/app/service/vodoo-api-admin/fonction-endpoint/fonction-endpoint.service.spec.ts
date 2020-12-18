import { TestBed } from '@angular/core/testing';

import { FonctionEndpointService } from './fonction-endpoint.service';

describe('FonctionEndpointService', () => {
  let service: FonctionEndpointService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FonctionEndpointService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
