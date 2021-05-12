import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/client">
      <Translate contentKey="global.menu.entities.client" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/adresse">
      <Translate contentKey="global.menu.entities.adresse" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/produit">
      <Translate contentKey="global.menu.entities.produit" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/type-de-produit">
      <Translate contentKey="global.menu.entities.typeDeProduit" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/commande">
      <Translate contentKey="global.menu.entities.commande" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/produit-commande">
      <Translate contentKey="global.menu.entities.produitCommande" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
