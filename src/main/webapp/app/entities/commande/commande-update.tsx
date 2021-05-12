import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IClient } from 'app/shared/model/client.model';
import { getEntities as getClients } from 'app/entities/client/client.reducer';
import { getEntity, updateEntity, createEntity, reset } from './commande.reducer';
import { ICommande } from 'app/shared/model/commande.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICommandeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CommandeUpdate = (props: ICommandeUpdateProps) => {
  const [clientId, setClientId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { commandeEntity, clients, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/commande');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getClients();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...commandeEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="commerceApp.commande.home.createOrEditLabel">
            <Translate contentKey="commerceApp.commande.home.createOrEditLabel">Create or edit a Commande</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : commandeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="commande-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="commande-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="commande-date">
                  <Translate contentKey="commerceApp.commande.date">Date</Translate>
                </Label>
                <AvField id="commande-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="moyentPaiementLabel" for="commande-moyentPaiement">
                  <Translate contentKey="commerceApp.commande.moyentPaiement">Moyent Paiement</Translate>
                </Label>
                <AvField id="commande-moyentPaiement" type="text" name="moyentPaiement" />
              </AvGroup>
              <AvGroup>
                <Label id="statutCommandeLabel" for="commande-statutCommande">
                  <Translate contentKey="commerceApp.commande.statutCommande">Statut Commande</Translate>
                </Label>
                <AvField id="commande-statutCommande" type="text" name="statutCommande" />
              </AvGroup>
              <AvGroup>
                <Label id="totalLabel" for="commande-total">
                  <Translate contentKey="commerceApp.commande.total">Total</Translate>
                </Label>
                <AvField id="commande-total" type="string" className="form-control" name="total" />
              </AvGroup>
              <AvGroup>
                <Label for="commande-client">
                  <Translate contentKey="commerceApp.commande.client">Client</Translate>
                </Label>
                <AvInput id="commande-client" type="select" className="form-control" name="clientId" required>
                  {clients
                    ? clients.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
                <AvFeedback>
                  <Translate contentKey="entity.validation.required">This field is required.</Translate>
                </AvFeedback>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/commande" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  clients: storeState.client.entities,
  commandeEntity: storeState.commande.entity,
  loading: storeState.commande.loading,
  updating: storeState.commande.updating,
  updateSuccess: storeState.commande.updateSuccess,
});

const mapDispatchToProps = {
  getClients,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CommandeUpdate);
