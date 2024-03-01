package com.influencer.demo;

import com.influencer.demo.entity.*;
import com.influencer.demo.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication(scanBasePackages = "com.influencer.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner demo(AccountService accountService, ProductService productService,
								  InfluencerAccountService influencerAccountService, BrandAccountService brandAccountService, PostService postService) {
		return args -> {
			boolean flag = true;
			while (flag) {
				int decision;
				Scanner sc = new Scanner(System.in);

				String string = "1: Post \n" +
						"2: Account \n" +
						"3: InfluencerAccount \n" +
						"4: BrandAccount \n" +
						"5: Product \n" +
						"6: Esci";
				System.out.println("Inserire il numero relativo all'entità che si desidera testare");
				System.out.println(string);

				do {
					decision = sc.nextInt();
					if (decision < 1 || decision > 6)
						System.out.println("Inserire valore compreso tra 1 e 6");
				} while (decision < 1 || decision > 6);


				switch (decision) {
					case 1:
						SwitchPost(postService, accountService, productService);
						break;
					case 2:
						SwitchAccount(postService, productService,accountService);
						break;
					case 3:
						SwitchInfluencer(postService, influencerAccountService, productService, accountService);
						break;
					case 4:
						SwitchBrand(brandAccountService, postService, accountService, productService);
						break;
					case 5:
						SwitchProduct(productService, accountService, postService);
						break;
					case 6:
						flag = true;
						System.exit(0);
						break;
				}
			}
		};
	}

	private static void SwitchPost(PostService postService, AccountService accountService, ProductService productService){
		Scanner sc = new Scanner(System.in);
		int decision;

		boolean exit = true;
		while (exit) {
			System.out.println("inserire il numero relativo alla funzionalità che si desidera testare");
			String stringProduct = "1: createPost()\n" +
					"2: updatepost()\n" +
					"3: deletePost()\n" +
					"4: getPost()\n" +
					"5: getPostList()\n" +
					"6: Indietro";
			System.out.println(stringProduct);

			do {
				decision = sc.nextInt();
				if (decision < 1 || decision > 6)
					System.out.println("Inserire valore compreso tra 1 e 6");
			} while (decision < 1 || decision > 6);

			switch (decision) {
				case 1:
					addPostTest(postService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 2:
					updatePostTest(postService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 3:
					deletePostTest(postService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 4:
					getPostTest(postService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 5:
					getPostListTest(postService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 6:
					exit = false;
					break;
			}
		}
	}

	private static void SwitchAccount(PostService postService, ProductService productService, AccountService accountService){
		Scanner sc = new Scanner(System.in);
		int decision;

		boolean exitAccount = true;
		while (exitAccount) {
			System.out.println("inserire il numero relativo alla funzionalità che si desidera testare");
			String stringAccount = "1: registerAccount()\n" +
					"2: deleteAccount()\n" +
					"3: updateBio()\n" +
					"4: getInfoAccount()\n" +
					"5: addFollowed()\n" +
					"6: deleteFollowed()\n" +
					"7: getFollowedList()\n"+
					"8: Indietro";
			System.out.println(stringAccount);
			do {
				decision = sc.nextInt();
				if (decision < 1 || decision > 8)
					System.out.println("Inserire valore compreso tra 1 e 8");
			} while (decision < 1 || decision > 8);

			switch (decision) {
				case 1:
					addAccount(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 2:
					deleteAccountTest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 3:
					updateBiotest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 4:
					getInfoAccountTest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 5:
					addFollowedTest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 6:
					deleteFollowedTest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 7:
					getFollowedTest(accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 8:
					exitAccount = false;
					break;
			}
		}
	}

	private static void SwitchInfluencer(PostService postService, InfluencerAccountService influencerAccountService, ProductService productService, AccountService accountService){
		Scanner sc = new Scanner(System.in);
		int decision;

		boolean exitInfluencer = true;
		while (exitInfluencer){
			String influencerString = "1: addProductSponsored()\n" +
					"2: deleteProductSponsored()\n" +
					"3: getProductSponsored()\n"+
					"4: addBrandCollaboration()\n"+
					"5: deleteBrandCollaboration()\n"+
					"6: getBrandAccountList()\n"+
					"7: getInfluencerById()\n"+
					"8: getAllInfluencer()\n"+
					"9: Indietro";
			System.out.println(influencerString);
			do {
				decision = sc.nextInt();
				if(decision<1 || decision>9)
					System.out.println("Inserire valore compreso tra 1 e 9");
			}while (decision<1 || decision>9);

			switch (decision){
				case 1:
					addProductSponsoredInfluencerTest(influencerAccountService,accountService,productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 2:
					deleteProductSponsoredInfluencerTest(influencerAccountService,accountService,productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 3:
					getProductSponsoredInfluencerTest(influencerAccountService,accountService,productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 4:
					addBrandCollaborationTest(influencerAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 5:
					deleteBrandCollaborationTest(influencerAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 6:
					getBrandAccountListTest(influencerAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 7:
					getInfluencerByIdTest(influencerAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 8:
					getAllInfluencerTest(influencerAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 9:
					exitInfluencer = false;
					break;
			}
		}

	}

	private static void SwitchBrand(BrandAccountService brandAccountService, PostService postService, AccountService accountService, ProductService productService){
		Scanner sc = new Scanner(System.in);
		int decision;

		boolean exitBrand = true;
		while (exitBrand){
			String brandString = "1: addproductForBrand()\n" +
					"2: deleteProductForBrand()\n" +
					"3: getBrandProducts()\n" +
					"4: findBrandsByProductPrice()\n"+
					"5: addInfluencerForBrand()\n"+
					"6: deleteInfluencerForBrand()\n"+
					"7: getInfluencerList()\n"+
					"8: getBrandByID()\n"+
					"9: getAllBrand()\n"+
					"10: Indietro";
			System.out.println(brandString);
			do {
				decision = sc.nextInt();
				if(decision<1 || decision>10)
					System.out.println("Inserire valore compreso tra 1 e 10");
			}while (decision<1 || decision>10);

			switch (decision) {
				case 1:
					addProductBrandTest(brandAccountService, accountService, productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 2:
					deleteProductBrandTest(brandAccountService, accountService, productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 3:
					getBrandProductsTest(brandAccountService, productService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 4:
					findBrandsByProductPriceTest(accountService, brandAccountService, productService);
					viewDatabase(postService, accountService, productService);
					break;

				case 5:
					addInfluencerForBrandTest(brandAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 6:
					deleteInfluencerForBrandTest(brandAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 7:
					getInfluencerListTest(brandAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 8:
					getBrandByIDTest(brandAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 9:
					getAllBrandTest(brandAccountService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 10:
					exitBrand = false;
					break;
			}
		}
	}

	private static void SwitchProduct(ProductService productService, AccountService accountService, PostService postService){
		Scanner sc = new Scanner(System.in);
		int decision;

		boolean exitProduct = true;
		while (exitProduct){


			System.out.println("inserire il numero relativo alla funzionalità che si desidera testare");
			String productString = "1: registerProduct()\n" +
					"2: deleteProduct()\n" +
					"3: updateProduct()\n" +
					"4: getProductInfo()\n" +
					"5: Indietro";
			System.out.println(productString);
			do {
				decision = sc.nextInt();
				if(decision<1 || decision>5)
					System.out.println("Inserire valore compreso tra 1 e 5");
			}while (decision<1 || decision>5);

			switch (decision) {
				case 1:
					productSaveTest(productService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 2:
					productDeleteTest(productService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 3:
					productUpdateTest(productService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 4:
					getProductInfoTest(productService, accountService);
					viewDatabase(postService, accountService, productService);
					break;

				case 5:
					exitProduct = false;
					break;
			}
		}
	}

	private static void viewDatabase(PostService postService, AccountService accountService, ProductService productService){
		Scanner sc = new Scanner(System.in);
		System.out.println("Verificare le modifiche nel database. \n"+
				"Premere invio per continuare con i test delle funzionalità");
		String string;
		do{
			string = sc.nextLine();
			if(!Objects.equals(string, ""))
				System.out.println("Premere invio, altri caratteri non sono ammessi!");
		}while(!Objects.equals(string, ""));
		checkValue(postService, accountService, productService);
	}

	private static void checkValue(PostService postService, AccountService accountService, ProductService productService){
		Scanner sc = new Scanner(System.in);
		int countPost =  postService.countElements();
		int countAccount =  accountService.countElements();
		int countProduct =  productService.countElements();
		if(countPost > 30 || countAccount > 30 || countProduct > 30){
			System.err.println("Raggiunto il numero massimo di elementi nel database!\n" +
					"Premere invio per arrestare l'applicazione");
			String string;
			do{
				string = sc.nextLine();
				if(!Objects.equals(string, ""))
					System.out.println("Premere invio, altri caratteri non sono ammessi!");
			}while(!Objects.equals(string, ""));
			System.exit(0);
		}
	}

	/**
	 * Adds a test case for creating and adding posts.
	 *
	 * @param postService The service responsible for managing posts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void addPostTest(PostService postService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Coca Cola", "bioCocaCola");
		InfluencerAccount influencerAccount = new InfluencerAccount("Will Smith", "bioWill Smith");

		accountService.registerAccount(brand);
		accountService.registerAccount(influencerAccount);

		Post post1 = new Post("new post");
		Post post2 = new Post("new post");

		postService.createPost(post1, brand);
		postService.createPost(post2, influencerAccount);
	}

	/**
	 * Adds a test case for deleting a post.
	 *
	 * @param postService The service responsible for managing posts.
	 * @param accountService The service responsible for managing accounts.
	 */

	private static void deletePostTest(PostService postService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Barilla", "bioBarilla");
		accountService.registerAccount(brand);
		Post post = new Post("new post");
		Post post2 = new Post("new post");
		postService.createPost(post, brand);
		postService.createPost(post2, brand);
		postService.deletePost(post);
	}

	/**
	 * Adds a test case for updating a post.
	 *
	 * @param postService The service responsible for managing posts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void updatePostTest(PostService postService, AccountService accountService){
		InfluencerAccount influencerAccount = new InfluencerAccount("Dwyane Johnson", "bioJohnson");
		accountService.registerAccount(influencerAccount);
		Post post = new Post("new post");
		postService.createPost(post, influencerAccount);
		postService.updatePost(post.getPostId(), "new description");
	}

	/**
	 * Adds a test case for retrieving a single post.
	 *
	 * @param postService The service responsible for managing posts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getPostTest(PostService postService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Apple", "bioApple");
		accountService.registerAccount(brand);
		Post post = new Post("new post");
		postService.createPost(post, brand);
		System.out.println(postService.getPost(post.getPostId()));
	}

	/**
	 * Adds a test case for retrieving a list of posts.
	 *
	 * @param postService The service responsible for managing posts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getPostListTest(PostService postService, AccountService accountService){
		InfluencerAccount influencerAccount = new InfluencerAccount("Samuel L. Jackson", "bioJackson");

		accountService.registerAccount(influencerAccount);
		accountService.registerAccount(influencerAccount);

		Post post1 = new Post("new post");
		Post post2 = new Post("new post");

		postService.createPost(post1, influencerAccount);
		postService.createPost(post2, influencerAccount);

		System.out.println(postService.getPostList(post1.getAccount().getId()));
	}

	/**
	 * Adds a test case for registering influencer and brand accounts.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void addAccount(AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Eddie Murphy", "bioMurphy");
		BrandAccount brand = new BrandAccount("Ferrero","bioFerrero");
		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
	}

	/**
	 * Adds a test case for deleting an account.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void deleteAccountTest(AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Jamie Foxx", "bioFoxx");
		BrandAccount brand = new BrandAccount("Logitech","bioLogitech");
		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		accountService.deleteAccount(influencer.getId());
	}

	/**
	 * Adds a test case for updating the bio of an account.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void updateBiotest(AccountService accountService){
		BrandAccount brand = new BrandAccount("Lenovo","bioLenovo");
		accountService.registerAccount(brand);
		final String newBio = "Bio aggiornata";
		accountService.updateBio(brand.getId(), newBio);
	}

	/**
	 * Adds a test case for retrieving information about an account.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getInfoAccountTest(AccountService accountService){
		BrandAccount brand = new BrandAccount("OnePlus","OnePlus");
		accountService.registerAccount(brand);
		System.out.println(accountService.getInfoAccount(brand.getId()));
	}

	/**
	 * Adds a test case for adding followed accounts.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void addFollowedTest(AccountService accountService){
		InfluencerAccount influencer1 = new InfluencerAccount("Laurence Fishbourne", "bioFishbourne");
		InfluencerAccount influencer2 = new InfluencerAccount("Morgan Freeman", "bioFreeman");
		InfluencerAccount influencer3 = new InfluencerAccount("Michael B. Jordan", "bioJordan");
		accountService.registerAccount(influencer1);
		accountService.registerAccount(influencer2);
		accountService.registerAccount(influencer3);

		ArrayList<Account> list = new ArrayList<>();
		list.add(influencer1);
		list.add(influencer2);
		list.add(influencer3);
		ArrayList<Long> accountIds = new ArrayList<>();

		for(Account account : list){
			accountIds.add(account.getId());
		}

		for(int i=1;i<accountIds.size();i++){
			accountService.addFollowed(accountIds.get(0), accountIds.get(i));
		}
	}

	/**
	 * Adds a test case for deleting followed accounts.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void deleteFollowedTest(AccountService accountService){
		InfluencerAccount influencer1 = new InfluencerAccount("Forest Whitaker", "bioWhitaker");
		InfluencerAccount influencer2 = new InfluencerAccount("Chadwick Boseman", "bioBoseman");
		accountService.registerAccount(influencer1);
		accountService.registerAccount(influencer2);

		ArrayList<Account> list = new ArrayList<>();
		list.add(influencer1);
		list.add(influencer2);
		ArrayList<Long> accountIds = new ArrayList<>();

		for(Account account : list){
			accountIds.add(account.getId());
		}

		for(int i=1;i<accountIds.size();i++){
			accountService.addFollowed(accountIds.get(0), accountIds.get(i));
		}

		accountService.deleteFollowed(accountIds.get(0),accountIds.get(1));
		System.out.println("Eliminata la relaziona tra account: " + accountIds.get(0) + " e " + accountIds.get(1));
	}

	/**
	 * Adds a test case for retrieving the list of followed accounts.
	 *
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getFollowedTest(AccountService accountService){
		InfluencerAccount influencer1 = new InfluencerAccount("Brad Pitt", "bioPitt");
		InfluencerAccount influencer2 = new InfluencerAccount("George Clooney", "bioClooney");
		accountService.registerAccount(influencer1);
		accountService.registerAccount(influencer2);

		ArrayList<Account> list = new ArrayList<>();
		list.add(influencer1);
		list.add(influencer2);
		ArrayList<Long> accountIds = new ArrayList<>();

		for(Account account : list){
			accountIds.add(account.getId());
		}

		for(int i=1;i<accountIds.size();i++){
			accountService.addFollowed(accountIds.get(0), accountIds.get(i));
		}

		System.out.println(accountService.getFollowedList(accountIds.get(0)));
	}

	/**
	 * Adds a test case for adding sponsored products to an influencer account.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 * @param productService The service responsible for managing products.
	 */
	private static void addProductSponsoredInfluencerTest(InfluencerAccountService influencerAccountService, AccountService accountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Xiaomi","bioXiaomi");
		Product product = new Product("Telefono",200,brand);

		BrandAccount brand2 = new BrandAccount("Armani","bioArmani");
		Product product2 = new Product("Cappello",300,brand2);

		InfluencerAccount influencer = new InfluencerAccount("Leonardo DiCaprio","bioDiCaprio");

		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		accountService.registerAccount(brand2);
		productService.registerProduct(product);
		productService.registerProduct(product2);

		influencerAccountService.addProductSponsored(influencer.getId(),product);
		influencerAccountService.addProductSponsored(influencer.getId(),product2);
	}

	/**
	 * Adds a test case for deleting a sponsored product from an influencer account.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 * @param productService The service responsible for managing products.
	 */
	private static void deleteProductSponsoredInfluencerTest(InfluencerAccountService influencerAccountService, AccountService accountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Feltrinelli","bioFeltrinelli");
		Product product = new Product("Libro",20,brand);
		InfluencerAccount influencer = new InfluencerAccount("Matt Damon","bioDamon");

		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		productService.registerProduct(product);

		influencerAccountService.addProductSponsored(influencer.getId(),product);

		influencerAccountService.deleteProductSponsored(influencer.getId(), product.getId());
		System.out.println("Eliminata la relaziona tra influencer: " + influencer.getId() + " e prodotto: " + product.getId());
	}

	private static void getProductSponsoredInfluencerTest(InfluencerAccountService influencerAccountService, AccountService accountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Amazon","bioAmazon");
		Product product = new Product("Kindle",100,brand);
		InfluencerAccount influencer = new InfluencerAccount("Ben Affleck","bioAffleck");

		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		productService.registerProduct(product);

		influencerAccountService.addProductSponsored(influencer.getId(),product);
		System.out.println(influencerAccountService.getProductSponsored(influencer.getId()));
	}

	/**
	 * Adds a test case for adding a brand collaboration to an influencer account.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void addBrandCollaborationTest(InfluencerAccountService influencerAccountService, AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Johnny Depp", "bioDepp");
		BrandAccount brand = new BrandAccount("Tencent", "bioTencent");
		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		influencerAccountService.addBrandCollaboration(influencer.getId(), brand);
	}

	/**
	 * Adds a test case for deleting a brand collaboration from an influencer account.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	public static void deleteBrandCollaborationTest(InfluencerAccountService influencerAccountService, AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Joe Bastianich", "bioBastianich");
		BrandAccount brand = new BrandAccount("McDonald's", "bioMcDonald's");

		accountService.registerAccount(influencer);
		accountService.registerAccount(brand);
		influencerAccountService.addBrandCollaboration(influencer.getId(), brand);

		influencerAccountService.deleteBrandCollaboration(influencer.getId(), brand.getId());
		System.out.println("Eliminata la relaziona tra influencer: " + influencer.getId() + " e brand: " + brand.getId());
	}

	/**
	 * Adds a test case for getting the list of brand accounts associated with an influencer account.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getBrandAccountListTest(InfluencerAccountService influencerAccountService, AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Bradley Cooper", "bioCooper");
		BrandAccount brand1 = new BrandAccount("HP", "bioHP");
		BrandAccount brand2 = new BrandAccount("Samsung", "bioSamsung");
		accountService.registerAccount(influencer);
		accountService.registerAccount(brand1);
		accountService.registerAccount(brand2);
		influencerAccountService.addBrandCollaboration(influencer.getId(), brand1);
		influencerAccountService.addBrandCollaboration(influencer.getId(), brand2);
		System.out.println(influencerAccountService.getBrandAccountList(influencer));
	}

	/**
	 * Adds a test case for getting an influencer account by its ID.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getInfluencerByIdTest(InfluencerAccountService influencerAccountService, AccountService accountService){
		InfluencerAccount influencer = new InfluencerAccount("Jennifer Aniston", "bioAniston");
		BrandAccount brand1 = new BrandAccount("Nivea", "bioNivea");
		accountService.registerAccount(influencer);
		accountService.registerAccount(brand1);
		influencerAccountService.addBrandCollaboration(influencer.getId(), brand1);
		System.out.println(influencerAccountService.getInfuencerById(influencer.getId()));
	}

	/**
	 * Adds a test case for getting a list of all influencer accounts.
	 *
	 * @param influencerAccountService The service responsible for managing influencer accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getAllInfluencerTest(InfluencerAccountService influencerAccountService, AccountService accountService){
		InfluencerAccount influencer1 = new InfluencerAccount("Robert Downey", "bioDowney");
		BrandAccount brand1 = new BrandAccount("Visa", "bioVisa");
		accountService.registerAccount(influencer1);
		accountService.registerAccount(brand1);
		influencerAccountService.addBrandCollaboration(influencer1.getId(), brand1);
		System.out.println(influencerAccountService.getAllInfluencer());
	}

	/**
	 * Adds a test case for adding products to a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 * @param productService The service responsible for managing products.
	 */
	private static void addProductBrandTest(BrandAccountService brandAccountService, AccountService accountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Prada","bioPrada");
		Product product = new Product("Pelliccia",270,brand);
		Product product2 = new Product("Sciarpa",300,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.registerProduct(product2);

		brandAccountService.addProductForBrand(brand.getId(), product);
		brandAccountService.addProductForBrand(brand.getId(), product2);
	}

	/**
	 * Adds a test case for deleting products from a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 * @param productService The service responsible for managing products.
	 */
	private static void deleteProductBrandTest(BrandAccountService brandAccountService, AccountService accountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Fendi","bioFendi");
		Product product = new Product("Maglione",70,brand);
		Product product2 = new Product("Pantalone",100,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.registerProduct(product2);

		brandAccountService.addProductForBrand(brand.getId(), product);
		brandAccountService.addProductForBrand(brand.getId(), product2);
		brandAccountService.deleteProductForBrand(brand.getId(), product.getId());
	}

	/**
	 * Adds a test case for getting the list of products associated with a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param productService The service responsible for managing products.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getBrandProductsTest(BrandAccountService brandAccountService, ProductService productService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Balocco","bioBalocco");
		Product product = new Product("Panettone",35,brand);
		Product product2 = new Product("Pandoro",30,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.registerProduct(product2);
		brandAccountService.addProductForBrand(brand.getId(), product);
		brandAccountService.addProductForBrand(brand.getId(), product2);

		System.out.println(brandAccountService.getBrandProducts(brand));
	}

	/**
	 * Adds a test case for finding brand accounts based on product prices.
	 *
	 * @param accountService The service responsible for managing accounts.
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param productService The service responsible for managing products.
	 */
	private static void findBrandsByProductPriceTest(AccountService accountService, BrandAccountService brandAccountService, ProductService productService){
		BrandAccount brand = new BrandAccount("Dell","bioDell");
		Product product = new Product("Computer",500,brand);
		Product product2 = new Product("Tablet",300,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.registerProduct(product2);
		brandAccountService.addProductForBrand(brand.getId(), product);
		brandAccountService.addProductForBrand(brand.getId(), product2);

		double maxPrice = 250;
		System.out.println(brandAccountService.findBrandsByProductPrice(maxPrice));
	}

	/**
	 * Adds a test case for adding influencers to a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void addInfluencerForBrandTest(BrandAccountService brandAccountService, AccountService accountService){
		BrandAccount brand1 = new BrandAccount("Alibaba", "bioAlibaba");
		BrandAccount brand2 = new BrandAccount("Nike", "bioNike");
		InfluencerAccount influencerAccount1 = new InfluencerAccount("Chris Pratt", "bioPratt");
		InfluencerAccount influencerAccount2 = new InfluencerAccount("Tom Cruise", "bioCruise");

		accountService.registerAccount(brand1);
		accountService.registerAccount(brand2);
		accountService.registerAccount(influencerAccount1);
		accountService.registerAccount(influencerAccount2);

		brandAccountService.addInfluencerForBrand(brand1.getId(), influencerAccount1);
		brandAccountService.addInfluencerForBrand(brand1.getId(), influencerAccount2);
	}

	/**
	 * Adds a test case for deleting influencers from a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void deleteInfluencerForBrandTest(BrandAccountService brandAccountService, AccountService accountService){
		BrandAccount brand1 = new BrandAccount("Adidas", "bioAdidas");
		InfluencerAccount influencerAccount1 = new InfluencerAccount("Robin Williams", "bioWilliams");

		accountService.registerAccount(brand1);
		accountService.registerAccount(influencerAccount1);

		brandAccountService.addInfluencerForBrand(brand1.getId(), influencerAccount1);
		brandAccountService.deleteInfluencerForBrand(brand1.getId(), influencerAccount1.getId());
		System.out.println("Eliminata la relaziona tra brand: " + brand1.getId() + " e influencer: " + influencerAccount1.getId());
	}

	/**
	 * Adds a test case for getting the list of influencers associated with a brand account.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getInfluencerListTest(BrandAccountService brandAccountService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Mastercard", "bioMastercard");
		InfluencerAccount influencer1 = new InfluencerAccount("Bruce Willis", "bioWillis");
		InfluencerAccount influencer2 = new InfluencerAccount("Tom Hanks", "bioHanks");

		accountService.registerAccount(brand);
		accountService.registerAccount(influencer1);
		accountService.registerAccount(influencer2);

		brandAccountService.addInfluencerForBrand(brand.getId(), influencer1);
		brandAccountService.addInfluencerForBrand(brand.getId(), influencer2);
		System.out.println(brandAccountService.getInfluencerList(brand));
	}

	/**
	 * Adds a test case for getting a brand account by its ID.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getBrandByIDTest(BrandAccountService brandAccountService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Tesla", "bioTesla");
		InfluencerAccount influencer = new InfluencerAccount("Michael J. Fox", "bioJ.Fox");
		accountService.registerAccount(brand);
		accountService.registerAccount(influencer);
		brandAccountService.addInfluencerForBrand(brand.getId(), influencer);
		System.out.println(brandAccountService.getBrandById(brand.getId()));
	}

	/**
	 * Adds a test case for getting a list of all brand accounts.
	 *
	 * @param brandAccountService The service responsible for managing brand accounts.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getAllBrandTest(BrandAccountService brandAccountService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Sky", "bioSky");
		InfluencerAccount influencer = new InfluencerAccount("Patrick Swayze", "bioSwayze");

		accountService.registerAccount(brand);
		accountService.registerAccount(influencer);

		brandAccountService.addInfluencerForBrand(brand.getId(), influencer);
		System.out.println(brandAccountService.getAllBrand());
	}

	/**
	 * Adds a test case for saving a product using the product service.
	 *
	 * @param productService The service responsible for managing products.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void productSaveTest(ProductService productService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Decathlon","bioDecathlon");
		Product product = new Product("Scarpe running",90,brand);
		Product product2 = new Product("Attrezzi palestra",500,brand);
		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.registerProduct(product2);
	}

	/**
	 * Adds a test case for deleting a product using the product service.
	 *
	 * @param productService The service responsible for managing products.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void productDeleteTest(ProductService productService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Esselunga","bioEsselunga");
		Product product1 = new Product("Pane",1,brand);
		Product product2 = new Product("Latte",3,brand);
		accountService.registerAccount(brand);
		productService.registerProduct(product1);
		productService.registerProduct(product2);

		productService.deleteProduct(product1.getId());
	}

	/**
	 * Adds a test case for updating a product using the product service.
	 *
	 * @param productService The service responsible for managing products.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void productUpdateTest(ProductService productService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Garmin","bioGarmin");
		Product product = new Product("Orologio",290,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		productService.updateProduct(product.getId());
	}

	/**
	 * Adds a test case for getting product information using the product service.
	 *
	 * @param productService The service responsible for managing products.
	 * @param accountService The service responsible for managing accounts.
	 */
	private static void getProductInfoTest(ProductService productService, AccountService accountService){
		BrandAccount brand = new BrandAccount("Nespresso","bioNespresso");
		Product product = new Product("Macchinetta Caffè",90,brand);

		accountService.registerAccount(brand);
		productService.registerProduct(product);
		System.out.println(productService.getProductInfo(product.getId()));
	}
}