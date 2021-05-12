import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypeDeProduit from './type-de-produit';
import TypeDeProduitDetail from './type-de-produit-detail';
import TypeDeProduitUpdate from './type-de-produit-update';
import TypeDeProduitDeleteDialog from './type-de-produit-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypeDeProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypeDeProduitUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypeDeProduitDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypeDeProduit} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypeDeProduitDeleteDialog} />
  </>
);

export default Routes;
