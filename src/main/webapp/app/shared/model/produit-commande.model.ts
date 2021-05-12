export interface IProduitCommande {
  id?: number;
  quantite?: number;
  produitId?: number;
  commandeId?: number;
}

export const defaultValue: Readonly<IProduitCommande> = {};
