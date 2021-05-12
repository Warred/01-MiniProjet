import { IProduit } from 'app/shared/model/produit.model';

export interface ITypeDeProduit {
  id?: number;
  type?: string;
  produits?: IProduit[];
}

export const defaultValue: Readonly<ITypeDeProduit> = {};
