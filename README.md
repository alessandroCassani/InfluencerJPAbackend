# Social Network

# GruppoLA
 
*Autori*:
 
- **Alessandro Cassani** (n. matricola: **920015**)
- **Luca Perfetti** (n. matricola: **919835**)
 

# Link GitLab
https://gitlab.com/2023_assignment3/2023_assignment3_influencer

# Descrizione dell'applicazione
Si propone un'applicazione che consente a un utente, identificato come account, di pubblicare zero o più post sulla piattaforma. Ogni account può assumere il ruolo di azienda che promuove i propri prodotti, oppure può agire come influencer, sponsorizzando i prodotti di un'azienda, o rappresentare direttamente il brand stesso. 

# Struttura del progetto
Nel progetto in questione sono presenti *cinque* entità:

- Post: postID, descrizione 
- Account: accountID, username, bio, followed_list, post_list 
- brandAccount: username, bio, influencer_list, brand_prodotti_list
- influencerAccount: username, bio, brand_collaborazioni_list, prodotti_sponsorizzati_list
- Product: productID, nome_prodotto, prezzo

# Relazioni tra entità
Nel contesto del progetto, sono state identificate e definite *tre* relazioni tra le entità coinvolte:

- **Pubblica (One-to-Many)**: relazione tra **Account** e **Post** ed è definita nel modo seguente: un account può pubblicare da zero a più post, mentre un post è associato a un unico account. 
- **Segue (Self-loop tra Account)**: relazione self-loop tra **Account** ed è definita nel modo seguente: un account può seguire altri account presenti sulla piattaforma. 
- **Sponsorizza (Many-to-Many)**: relazione tra le entità **brandAccount** e **influencerAccount** ed è caratterizzata dalla seguente definizione: un influencer può sponsorizzare da zero a più brand, mentre un brand può essere sponsorizzato da zero a più influencer. Relazione di tipo *many-to-many*, in cui entrambe le entità possono essere associate a molteplici istanze dell'altra. 
