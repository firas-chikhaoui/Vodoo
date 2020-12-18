import { TestBed } from '@angular/core/testing';

import { UtilisateurEndpointService } from './utilisateur-endpoint.service';

describe('UtilisateurEndpointService', () => {
  let service: UtilisateurEndpointService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UtilisateurEndpointService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
