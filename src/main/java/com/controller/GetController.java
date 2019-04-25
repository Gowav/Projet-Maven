package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.Ville;
import com.dao.DAOFactory;
import com.dao.VilleDAO;

@RestController
public class GetController {
	private VilleDAO villeDAO;
	private List<Ville> liste;

	@RequestMapping(value="/get",method=RequestMethod.GET)
	@ResponseBody
	public String get(@RequestParam(required=false, value="codeCommuneINSEE")String value) {
		
		DAOFactory daoFactory = new DAOFactory() ;
		this.villeDAO = daoFactory.getVilleDao();
		
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(value);
		
		if(value!=null) {
			liste = this.villeDAO.trouver(ville);
		}else {
			liste = this.villeDAO.lister();
		}
		
		String str = "";
		for(Ville v : liste) {
			str += v.getCodeCommuneINSEE()+","+v.getNomCommune()+","+v.getCodePostal()+","+v.getLibelleAcheminement()+","
					+v.getLigne5()+","+v.getLatitude()+","+v.getLongitude()+";";
			//System.out.println(str);
		}
		
		return str;
	}
	
	@RequestMapping(value="/post",method=RequestMethod.POST)
	@ResponseBody
	public void post(@RequestBody Ville ville) {
		
		DAOFactory daoFactory = new DAOFactory() ;
		this.villeDAO = daoFactory.getVilleDao();
		this.villeDAO.creer(ville);
		//System.out.println("Insertion réussie!");
		
	}
	
	@RequestMapping(value="/put",method=RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestParam(required=true, value="codeCommuneINSEE")String value,
			@RequestParam(required=false, value="newCodeCommuneINSEE")String newCodeCommune,
			@RequestParam(required=false, value="codePostal")String codePostal,
			@RequestParam(required=false, value="nomCommune")String nomCommune,
			@RequestParam(required=false, value="libelleAcheminement")String libelleAcheminement,
			@RequestParam(required=false, value="ligne5")String ligne5,
			@RequestParam(required=false, value="latitude")String latitude,
			@RequestParam(required=false, value="longitude")String longitude) {
		
		DAOFactory daoFactory = new DAOFactory() ;
		this.villeDAO = daoFactory.getVilleDao();
		
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(newCodeCommune);
		ville.setCodePostal(codePostal);
		if(nomCommune!=null)
			ville.setNomCommune(nomCommune.toUpperCase());
		if(libelleAcheminement!=null)
			ville.setLibelleAcheminement(libelleAcheminement.toUpperCase());
		if(ligne5!=null)
			ville.setLigne5(ligne5.toUpperCase());
		ville.setLatitude(latitude);
		ville.setLongitude(longitude);

		this.villeDAO.modifier(value,ville);
		//System.out.println("Mise à jour de la ville réussie!");
		
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@RequestParam(required=false, value="codeCommuneINSEE")String value) {
		
		DAOFactory daoFactory = new DAOFactory() ;
		this.villeDAO = daoFactory.getVilleDao();
		
		Ville ville = new Ville();
		ville.setCodeCommuneINSEE(value);

		this.villeDAO.supprimer(ville);
		//System.out.println("Suppression réussie!");
		
	}

}
