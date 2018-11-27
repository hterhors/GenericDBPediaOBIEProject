package de.hterhors.obie.projects.dbpedia.ie.parameter;

import java.util.Arrays;
import java.util.HashSet;

import de.hterhors.obie.ml.corpus.distributor.AbstractCorpusDistributor;
import de.hterhors.obie.ml.corpus.distributor.ByInstanceNameDistributor;

public class ByNameDist {

	final public static AbstractCorpusDistributor corpusDistributor = new ByInstanceNameDistributor.Builder()
			.setNamesOfTrainingInstances(new HashSet<>(Arrays.asList("Bobby_Mills_(footballer)")))
			.setNamesOfTestInstances(new HashSet<>(Arrays.asList("Len_Oliver_(footballer)",
					"Gunnar_Olsson_(footballer)", "Serafim_Neves", "David_McGurk", "Aleksandr_Olerski",
					"Harry_Moore_(footballer)", "Bobby_Howlett", "Barry_Dominey", "Jeffrey_Gouweleeuw", "Everton_Sena",
					"Yang_Seung-kook", "Chris_Philipps", "Mobin_Rai", "Fabien_Cool", "James_Lamont_(footballer)",
					"Tom_Wade", "John_Brown_(footballer,_born_March_1940)", "Peter_Gideon", "Mohamed_Kaci-Said",
					"Harm_Zeinstra", "Mathieu_Deplagne", "Andre_Anis", "Charles_Geerts_(footballer)",
					"Terry_Baker_(footballer)", "Nassir_Al-Ghanim", "Valeriy_Boychenko", "Bozhidar_Mitrev",
					"Arnie_Sidebottom", "Ambrose_Harris", "Tarmo_Saks", "Ralph_O'Donnell", "Jamie_Jones_(footballer)",
					"Kim_Jong-min_(footballer)", "Buenaventura_Ferreira", "Andrea_Ferrari_(footballer)", "Joseph_Kamga",
					"Abdullah_Mayouf", "Darren_Bastow", "Imre_Kiss_(footballer_born_1957)", "Delroy_Scott",
					"Mattia_Dal_Bello", "Mohamad_Atwi", "Jimmy_Thorpe", "Sadok_Sassi", "Michalis_Karas",
					"Steve_Seargeant", "Anthony_Wordsworth", "Marius_Zarn", "Vahid_Heydarieh", "Ernesto_Brown",
					"Cornel_Chin-Sue", "Lalrinzuala_Khiangte", "Ayanda_Gcaba", "Jason_Davis_(footballer)", "Jack_Mew",
					"Peter_Hill_(footballer)", "Adrian_Bird_(footballer)", "Hugo_Seixas", "Arthur_Stanton",
					"Paul_Fleming_(footballer)", "Fadel_Jilal", "Nikola_Parshanov", "Dany_Maury",
					"Bernard_Hall_(footballer)", "Adedeji_Oshilaja", "Alessandro_Bianchi_(footballer_born_1989)",
					"Billal_Zouani", "Tom_Allan_(footballer)", "Ibra_Agbo", "Mark_Lamont", "Altay_Kahraman",
					"Gael_Margulies", "Jim_Duncan_(footballer)", "Aden_Charmakeh", "Gilles_Dewaele", "Sam_Hignett",
					"Daniel_Mudau", "James_Vance_(footballer)", "Ugo_Okoye", "Azidan_Sarudin", "Ke_Seung-woon",
					"Mark_Sherrod", "Jon_McLaughlin_(footballer)", "Adixi_Lenzivio", "Bradley_Beattie", "Hymie_Kloner",
					"Cameron_Lancaster", "Gyula_Prassler", "Tommy_Black_(footballer,_born_1908)", "Clint_Boulton",
					"Yussef_Al-Suwayed", "Norman_Chalk", "Tracy_Ducar", "Tyson_Caiado", "Stan_Cutting", "Mirko_Bigazzi",
					"Vince_Kenny", "Gideon_V._Way", "Julius_Ubido", "Reidar_Olsen", "Noel_George", "Arne_Linderholm",
					"Ahmad_Sabri_Ismail", "Oh_Yoon-kyung", "Rain_Vessenberg", "Abdelmajid_Lamriss", "Stefan_Velichkov",
					"Jaime_Huerta", "Jakob_Haugaard", "Jack_Cropley", "Arvo_Kraam", "Alex_McLintock", "Nikhil_Kadam",
					"Miguel_Segura", "Geoff_Coffin", "Barry_Mealand", "Steve_Tutill", "Piotr_Robakowski",
					"Adam_Nichols", "Steve_Middleton", "Callum_Reilly_(footballer)", "Alari_Lell", "Plamen_Krachunov",
					"Yang_Qipeng", "Carlos_Mateus_Ximenes", "Benjamin_Huggel", "Erik_Persson", "Antony_Lecointe",
					"Waleed_Al-Jasem", "Rudolf_Pichler", "Alan_Dennis", "Ivan_Toney", "Jake_Cuenca",
					"Carlos_Contreras_(footballer)", "Benjamin_Kirsten", "Sandy_Mutch", "Shakeel_Abbasi",
					"Bill_Hart_(footballer)", "Arthur_Cowell", "Walter_Balmer_(footballer)", "Oleksandr_Aksyonov",
					"Hussain_Al-Romaihi", "Cindy_Daws", "Alan_Dunne", "Charles_Dennington", "Johan_Eklund")))
			.build();
//	final public static AbstractCorpusDistributor corpusDistributor = new ByInstanceNameDistributor.Builder()
//			.setNamesOfTrainingInstances(new HashSet<>(Arrays.asList("Craig_Bingham", "Arthur_Topham", "Ben_Herd",
//					"Howard_Johnson_(footballer)", "Oliver_Gill", "Tony_Emery", "Juan_Alfonso_Valle", "Lee_Chang-myung",
//					"Obi_Onyeike", "Noel_Simpson", "Urbano_Rivera", "Johann_Windisch", "Abdullah_Al-Dosari",
//					"Brima_Bangura", "William_Berry_(footballer)", "Maria_Mitkou", "Bill_Jaques", "Jim_Gallacher",
//					"Abdul-Jabar_Hashim_Hanoon", "Mahmood_Abdulla", "Lee_Margerison", "Enzo_Crivelli",
//					"Tommy_Graham_(English_footballer)", "Isaac_Shai", "Vidin_Apostolov", "Boboi_Singh",
//					"Dinesh_Singh_(footballer)", "Jimmy_Greenock", "Ross_McFarlane_(footballer)",
//					"Kevin_Welsh_(footballer)", "Zeferino_Martins", "Selwyn_Whalley", "Gwyn_Hughes_(footballer)",
//					"Billy_Cooper_(footballer)", "Roman_Nykytyuk", "Leonel_Parris", "David_Bueso", "Akil_Byron",
//					"Bert_Fenwick", "Ian_Atkinson", "Dimitar_Kostov", "Jim_Blacker", "Robbie_Haw", "Andrew_Dawber",
//					"Lahcen_Ouadani", "Allal_Ben_Kassou", "Dele_Alli", "David_Letham", "Renan_dos_Santos",
//					"Muayad_Al-Haddad", "Ashish_Chettri", "Darren_Holden_(footballer)", "Miguel_Van_Damme",
//					"Byron_Lawrence", "Ian_Johnstone", "Tony_Miller_(footballer)", "Sophus_Hansen", "Modesto_Denis",
//					"Dumitru_Stajila", "Josef_Majer", "Alan_Buck", "Tom_Brooks_(footballer)", "Marcelino_Vargas",
//					"Dzintar_Klavan", "Paul_Flowers_(footballer)", "Shaun_Mawer", "Sjors_Verdellen",
//					"Mohamed_Ben_Mouza", "Phil_Thomas_(footballer)", "Nawab_Zeeshan", "Fred_Pelly", "Luis_Reyna",
//					"Archibald_Barton", "Filipe_de_Souza_Conceicao", "Albert_Read_(footballer)",
//					"Rod_Cameron_(footballer)", "Phil_Barlow", "Malcolm_Newlands", "Tonny_Brochmann", "Erich_Hasenkopf",
//					"Ioannis_Stefas", "Arthur_Hallworth", "Tommy_McQuaid", "Leonne_Stentler", "Hassan_Mattar",
//					"Martin_van_Leeuwen", "Knut_Hansson", "Rudy_Getzinger", "Milen_Ivanov", "Gary_Riddell",
//					"David_Buck_(footballer)", "Daniel_Pappoe", "Liam_Gray", "Sjaak_Troost", "Manuel_D'Souza",
//					"Redouane_Drici", "William_Horne_(footballer)", "Harry_Gilberg", "Fred_Furniss", "Clovis_Kamdjo",
//					"Christian_Dean", "Michael_Maidens", "David_Soames", "Curtis_Main", "Peter_Fregene", "Bill_Ruffell",
//					"Adam_Bedell", "Bimal_Magar", "Packie_Bonner", "William_Carrier", "Richard_Lee_(footballer)",
//					"Tony_Hawksworth", "Antoine_Gounet", "Sheldon_Govier", "Munir_El_Haddadi",
//					"James_Vincent_(footballer)", "Gianpiero_Combi", "Stan_Newsham", "John_Baines_(footballer)",
//					"Matthew_Coad", "Mohamed_Oulhaj", "Wally_Hazelden", "Aldo_Poy", "Phil_Griggs", "Lee_Jong-ho",
//					"Peter_Sanders_(sportsman)", "Dimitris_Machairas", "Michael_Galea", "Jonathan_Cubero",
//					"Pape_Latyr_N'Diaye", "Allen_Larue", "Eliseo_Brown", "Matt_Sparrow", "Tommy_Spurr",
//					"Andy_Davis_(British_Virgin_Islands_footballer)", "Obed_Owusu", "Abhijit_Das", "Dowlyn_Daly",
//					"Andy_Halls", "Joe_Bryan", "Maurice_Norman", "Tom_Eastman", "Matthew_Cooper_(footballer)",
//					"Taariq_Fielies", "Joseph_Gryzik", "Carlos_Nvomo", "Dallas_Jaye", "Conny_Johansson",
//					"Alfred_Dobson", "Geoff_Smith_(footballer)", "Artur_Dyson", "Raafat_Attia", "Roel_Wiersma",
//					"Ayron_Verkindere", "David_Svensson", "Jack_Deakin", "Les_Dicker", "Billy_Beaumont",
//					"Prasenjit_Ghosh", "Mohd_Shahrazen_Said", "Blagoy_Makendzhiev", "Roar_Strand", "Ludivine_Diguelman",
//					"Carlos_Lett", "Wardun_Yusof", "Shaun_Barker", "Mursyid_Effendi", "Alfredo_Brown", "Peter_Kunter")))
//			.setNamesOfTestInstances(new HashSet<>(Arrays.asList("Len_Oliver_(footballer)",
//					"Gunnar_Olsson_(footballer)", "Serafim_Neves", "David_McGurk", "Aleksandr_Olerski",
//					"Harry_Moore_(footballer)", "Bobby_Howlett", "Barry_Dominey", "Jeffrey_Gouweleeuw", "Everton_Sena",
//					"Yang_Seung-kook", "Chris_Philipps", "Mobin_Rai", "Fabien_Cool", "James_Lamont_(footballer)",
//					"Tom_Wade", "John_Brown_(footballer,_born_March_1940)", "Peter_Gideon", "Mohamed_Kaci-Said",
//					"Harm_Zeinstra", "Mathieu_Deplagne", "Andre_Anis", "Charles_Geerts_(footballer)",
//					"Terry_Baker_(footballer)", "Nassir_Al-Ghanim", "Valeriy_Boychenko", "Bozhidar_Mitrev",
//					"Arnie_Sidebottom", "Ambrose_Harris", "Tarmo_Saks", "Ralph_O'Donnell", "Jamie_Jones_(footballer)",
//					"Kim_Jong-min_(footballer)", "Buenaventura_Ferreira", "Andrea_Ferrari_(footballer)", "Joseph_Kamga",
//					"Abdullah_Mayouf", "Darren_Bastow", "Imre_Kiss_(footballer_born_1957)", "Delroy_Scott",
//					"Mattia_Dal_Bello", "Mohamad_Atwi", "Jimmy_Thorpe", "Sadok_Sassi", "Michalis_Karas",
//					"Steve_Seargeant", "Anthony_Wordsworth", "Marius_Zarn", "Vahid_Heydarieh", "Ernesto_Brown",
//					"Cornel_Chin-Sue", "Lalrinzuala_Khiangte", "Ayanda_Gcaba", "Jason_Davis_(footballer)", "Jack_Mew",
//					"Peter_Hill_(footballer)", "Adrian_Bird_(footballer)", "Hugo_Seixas", "Arthur_Stanton",
//					"Paul_Fleming_(footballer)", "Fadel_Jilal", "Nikola_Parshanov", "Dany_Maury",
//					"Bernard_Hall_(footballer)", "Adedeji_Oshilaja", "Alessandro_Bianchi_(footballer_born_1989)",
//					"Billal_Zouani", "Tom_Allan_(footballer)", "Ibra_Agbo", "Mark_Lamont", "Altay_Kahraman",
//					"Gael_Margulies", "Jim_Duncan_(footballer)", "Aden_Charmakeh", "Gilles_Dewaele", "Sam_Hignett",
//					"Daniel_Mudau", "James_Vance_(footballer)", "Ugo_Okoye", "Azidan_Sarudin", "Ke_Seung-woon",
//					"Mark_Sherrod", "Jon_McLaughlin_(footballer)", "Adixi_Lenzivio", "Bradley_Beattie", "Hymie_Kloner",
//					"Cameron_Lancaster", "Gyula_Prassler", "Tommy_Black_(footballer,_born_1908)", "Clint_Boulton",
//					"Yussef_Al-Suwayed", "Norman_Chalk", "Tracy_Ducar", "Tyson_Caiado", "Stan_Cutting", "Mirko_Bigazzi",
//					"Vince_Kenny", "Gideon_V._Way", "Julius_Ubido", "Reidar_Olsen", "Noel_George", "Arne_Linderholm",
//					"Ahmad_Sabri_Ismail", "Oh_Yoon-kyung", "Rain_Vessenberg", "Abdelmajid_Lamriss", "Stefan_Velichkov",
//					"Jaime_Huerta", "Jakob_Haugaard", "Jack_Cropley", "Arvo_Kraam", "Alex_McLintock", "Nikhil_Kadam",
//					"Miguel_Segura", "Geoff_Coffin", "Barry_Mealand", "Steve_Tutill", "Piotr_Robakowski",
//					"Adam_Nichols", "Steve_Middleton", "Callum_Reilly_(footballer)", "Alari_Lell", "Plamen_Krachunov",
//					"Yang_Qipeng", "Carlos_Mateus_Ximenes", "Benjamin_Huggel", "Erik_Persson", "Antony_Lecointe",
//					"Waleed_Al-Jasem", "Rudolf_Pichler", "Alan_Dennis", "Ivan_Toney", "Jake_Cuenca",
//					"Carlos_Contreras_(footballer)", "Benjamin_Kirsten", "Sandy_Mutch", "Shakeel_Abbasi",
//					"Bill_Hart_(footballer)", "Arthur_Cowell", "Walter_Balmer_(footballer)", "Oleksandr_Aksyonov",
//					"Hussain_Al-Romaihi", "Cindy_Daws", "Alan_Dunne", "Charles_Dennington", "Johan_Eklund")))
//			.build();

}
