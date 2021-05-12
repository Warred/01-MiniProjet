export interface IAdresse {
  id?: number;
  numero?: string;
  rue?: string;
  codePostal?: string;
  ville?: string;
  clientId?: number;
}

export const defaultValue: Readonly<IAdresse> = {};
