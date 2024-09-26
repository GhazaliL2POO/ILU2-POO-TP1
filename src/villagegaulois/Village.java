package villagegaulois;

import java.util.Iterator;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum,int nbrEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	private static class Marche {
		private Etal[] etals;

		private Marche(int nbrEtal) {
			this.etals = new Etal[nbrEtal];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}

		private int trouverEtalLibre() {
			int i = 0;
			for (i = 0; i != etals.length && etals[i].isEtalOccupe(); i++)
				;
			if (i != etals.length)
				return i;
			else
				return -1;
		}

		private Etal[] trouverEtals(String produit) {
			int nbrPrd = 0;
			for (int i = 0; i < etals.length; ++i) {
				if (etals[i].contientProduit(produit)) {
					nbrPrd++;
				}
			}
			Etal[] etalPrd = new Etal[nbrPrd];

			for (int i = 0, j = 0; i < etals.length; ++i) {
				if (etals[i].contientProduit(produit)) {
					etalPrd[j] = etals[i];
					j++;
				}
			}
			return etalPrd;
		}

		private Etal trouverVendeur(Gaulois gaulois) {
			int i = 0;
			for (i = 0; i < etals.length && !(etals[i].getVendeur().equals(gaulois)); i++)
				;
			if (i != etals.length)
				return etals[i];
			else
				return null;

		}

		private String afficherMarche() {
			int nbEtallibre = 0;
			StringBuilder chaine= new StringBuilder();
			for (int i = 0; i < etals.length; i++) {
				if(!etals[i].isEtalOccupe())
					nbEtallibre++;
				else {
					
					chaine.append(etals[i].afficherEtal());
				}
			}
			if(nbEtallibre>0)
				return chaine+("\\nil reste\"+nbEtallibre+\" étals non utilisés dans le marché");
			else
				return chaine+("\n tous les étals sont utilisés dans le marché");
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}