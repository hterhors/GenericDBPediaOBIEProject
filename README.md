# GenericDBPediaOBIEProject

The generic version of the SoccerPlayer project. This Project contains various different data sets and ontologies.

**NOTE** This project is still under heavy development!

**Quick Start**

1)  Clone necessary projects (e.g. in terminal) :

git clone https://github.com/hterhors/OBIECore.git;

git clone https://github.com/hterhors/OWL2JavaBin.git;

git clone https://github.com/hterhors/OBIEMachineLearningFramework.git;

git clone https://github.com/hterhors/DBPediaCorpusExtractor.git;

git clone https://github.com/ag-sc/BIRE.git -b simplified-api;

2)  Start IDE of choice (e.g. Eclipse):

Make sure that maven is installed (per default in many IDEs). 

3)  Import Maven projects:

Right-click "Import..." -> Existing Maven Projects. Browse for git folder -> Select all 5 projects 

4)  Update all maven projects:

In Eclipse right-click -> Maven -> "Update Project..." select all projects.


TODO: further describe... 

1) Create new DBPedia Ontology with *DBPediaCorpusExtractor*
2) Create new OntologyEnvironment
3) Create Java Binaries using *CreateOntologyJavaBinaries*
4) Create ProjectEnvironment for the new ontology
5) Create Named Entity Recognition and Linker (NERL) for new the Ontology (Basically create pattern and interpreter for datatype values)
6) Extract and create the raw corpus from DBPedia using  *GenericRawCorpusExtractor* (Contains only the text and the corresponding template in the text, but no textual annotations)
7) Create BigramCorpus with *BigramCorpusCreator* (Enrich the raw corpus with textual annotations based on the NERL) 
8) Run some tests: *ComputeUpperBound*, *ComputeRandomBaseline* *ComputeHighFreqBaseline*
9) Run extraction *StartExtraction*
10) Implement new and experiment with existing feature templates and experiment with 


