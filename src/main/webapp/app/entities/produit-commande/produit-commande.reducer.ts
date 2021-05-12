import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProduitCommande, defaultValue } from 'app/shared/model/produit-commande.model';

export const ACTION_TYPES = {
  FETCH_PRODUITCOMMANDE_LIST: 'produitCommande/FETCH_PRODUITCOMMANDE_LIST',
  FETCH_PRODUITCOMMANDE: 'produitCommande/FETCH_PRODUITCOMMANDE',
  CREATE_PRODUITCOMMANDE: 'produitCommande/CREATE_PRODUITCOMMANDE',
  UPDATE_PRODUITCOMMANDE: 'produitCommande/UPDATE_PRODUITCOMMANDE',
  DELETE_PRODUITCOMMANDE: 'produitCommande/DELETE_PRODUITCOMMANDE',
  RESET: 'produitCommande/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProduitCommande>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProduitCommandeState = Readonly<typeof initialState>;

// Reducer

export default (state: ProduitCommandeState = initialState, action): ProduitCommandeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PRODUITCOMMANDE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PRODUITCOMMANDE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PRODUITCOMMANDE):
    case REQUEST(ACTION_TYPES.UPDATE_PRODUITCOMMANDE):
    case REQUEST(ACTION_TYPES.DELETE_PRODUITCOMMANDE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PRODUITCOMMANDE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PRODUITCOMMANDE):
    case FAILURE(ACTION_TYPES.CREATE_PRODUITCOMMANDE):
    case FAILURE(ACTION_TYPES.UPDATE_PRODUITCOMMANDE):
    case FAILURE(ACTION_TYPES.DELETE_PRODUITCOMMANDE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUITCOMMANDE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUITCOMMANDE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PRODUITCOMMANDE):
    case SUCCESS(ACTION_TYPES.UPDATE_PRODUITCOMMANDE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PRODUITCOMMANDE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/produit-commandes';

// Actions

export const getEntities: ICrudGetAllAction<IProduitCommande> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PRODUITCOMMANDE_LIST,
  payload: axios.get<IProduitCommande>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProduitCommande> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PRODUITCOMMANDE,
    payload: axios.get<IProduitCommande>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProduitCommande> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PRODUITCOMMANDE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProduitCommande> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PRODUITCOMMANDE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProduitCommande> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PRODUITCOMMANDE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
