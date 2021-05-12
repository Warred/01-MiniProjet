import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProduitCommande from './produit-commande';
import ProduitCommandeDetail from './produit-commande-detail';
import ProduitCommandeUpdate from './produit-commande-update';
import ProduitCommandeDeleteDialog from './produit-commande-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProduitCommandeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProduitCommandeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProduitCommandeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProduitCommande} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProduitCommandeDeleteDialog} />
  </>
);

export default Routes;
