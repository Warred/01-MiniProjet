import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypeDeProduit, defaultValue } from 'app/shared/model/type-de-produit.model';

export const ACTION_TYPES = {
  FETCH_TYPEDEPRODUIT_LIST: 'typeDeProduit/FETCH_TYPEDEPRODUIT_LIST',
  FETCH_TYPEDEPRODUIT: 'typeDeProduit/FETCH_TYPEDEPRODUIT',
  CREATE_TYPEDEPRODUIT: 'typeDeProduit/CREATE_TYPEDEPRODUIT',
  UPDATE_TYPEDEPRODUIT: 'typeDeProduit/UPDATE_TYPEDEPRODUIT',
  DELETE_TYPEDEPRODUIT: 'typeDeProduit/DELETE_TYPEDEPRODUIT',
  RESET: 'typeDeProduit/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeDeProduit>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TypeDeProduitState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeDeProduitState = initialState, action): TypeDeProduitState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPEDEPRODUIT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEDEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEDEPRODUIT):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEDEPRODUIT):
    case REQUEST(ACTION_TYPES.DELETE_TYPEDEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPEDEPRODUIT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEDEPRODUIT):
    case FAILURE(ACTION_TYPES.CREATE_TYPEDEPRODUIT):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEDEPRODUIT):
    case FAILURE(ACTION_TYPES.DELETE_TYPEDEPRODUIT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEDEPRODUIT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEDEPRODUIT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEDEPRODUIT):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEDEPRODUIT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEDEPRODUIT):
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

const apiUrl = 'api/type-de-produits';

// Actions

export const getEntities: ICrudGetAllAction<ITypeDeProduit> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEDEPRODUIT_LIST,
  payload: axios.get<ITypeDeProduit>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITypeDeProduit> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEDEPRODUIT,
    payload: axios.get<ITypeDeProduit>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITypeDeProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEDEPRODUIT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeDeProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEDEPRODUIT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeDeProduit> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEDEPRODUIT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
