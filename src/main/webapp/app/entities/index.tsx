import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Client from './client';
import Adresse from './adresse';
import Produit from './produit';
import TypeDeProduit from './type-de-produit';
import Commande from './commande';
import ProduitCommande from './produit-commande';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}client`} component={Client} />
      <ErrorBoundaryRoute path={`${match.url}adresse`} component={Adresse} />
      <ErrorBoundaryRoute path={`${match.url}produit`} component={Produit} />
      <ErrorBoundaryRoute path={`${match.url}type-de-produit`} component={TypeDeProduit} />
      <ErrorBoundaryRoute path={`${match.url}commande`} component={Commande} />
      <ErrorBoundaryRoute path={`${match.url}produit-commande`} component={ProduitCommande} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
