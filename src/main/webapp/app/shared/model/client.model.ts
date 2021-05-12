import { ICommande } from 'app/shared/model/commande.model';

export interface IClient {
  id?: number;
  telephone?: string;
  userId?: number;
  adresseId?: number;
  commandes?: ICommande[];
}

export const defaultValue: Readonly<IClient> = {};
