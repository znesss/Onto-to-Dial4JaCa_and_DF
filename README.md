# About

Having a tool that enables agents to communicate through Dialogflow ([Dial4JaCa](https://github.com/smart-pucrs/Dial4JaCa)), together with the tool which is responsible of making queries on the Ontology file (Onto4JaCa), provides an infrastructure capable of reasoning on what the system knows as a wise agent, before answering to the user.

By translating the requests and responses for the two parties, Dial4JaCa works as a bridge between a chatbot and an expert system. But a lot of hand made encoding is required to make all the components (intents, plans of triggers) of these two parties consistent.

This is time consuming and raises the risk of errors, being entirely manual. Automatising some of these manual steps is the goal of Ontology-to-JaCaMo_and_Dialogflow.

The proposed generator, is responsible of reading the **customised domain-specific ontology**, and of generating the files (**jason agent file and zip file**) to feed the parties.

The required tools and a short tutorial can be found in [wiki](https://github.com/znesss/Ontology-to-JaCaMo_and_Dialogflow/wiki).
