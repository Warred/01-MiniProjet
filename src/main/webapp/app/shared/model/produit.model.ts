export interface IProduit {
  id?: number;
  nom?: string;
  prix?: number;
  description?: string;
  typeId?: number;
}

export const defaultValue: Readonly<IProduit> = {};
