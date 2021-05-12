import { Moment } from 'moment';
import { IProduitCommande } from 'app/shared/model/produit-commande.model';

export interface ICommande {
  id?: number;
  date?: string;
  moyentPaiement?: string;
  statutCommande?: string;
  total?: number;
  paniers?: IProduitCommande[];
  clientId?: number;
}

export const defaultValue: Readonly<ICommande> = {};
