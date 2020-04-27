package Core;

public class BasketTest {

    public class BasketTest {

        public ProductPage productPage;
        public CommonActions commonActions;
        public TopBanner topBanner;
        public ShoppingBasketPage shoppingBasketPage;

        @BeforeEach
        public void setUp() {
            WebDriver driver = DriverManager.getDriver();
            productPage = new ProductPage(driver);
            commonActions = new CommonActions(driver);
            topBanner = new TopBanner();
            shoppingBasketPage = new ShoppingBasketPage(driver);
        }

        @Test
        public void canDeleteItemsFromBasket() throws InterruptedException {
            productPage.addItemToBasket("book1");
            productPage.addItemToBasket("book2");
            commonActions.removeAllItemsFromBasket();
            topBanner.selectShoppingCartIcon();
            shoppingBasketPage = new ShoppingBasketPage(driver);
            shoppingBasketPage.verifyOnShoppingBasketPage();
            assertThat("The basket was not empty as expected", topBanner.getNumberOfItemsListedInShoppingCartIcon(), is("0"));
            assertThat("The shopping basket message wasn't as expected", shoppingBasketPage.getShoppingCartMessage(), is("Your Amazon basket is empty"));
        }

        @Test
        public void canAddSingleItemToBasket() {
            productPage.addItemToBasket("book1");
            assertThat("The number of items in the basket did not match that expected", topBanner.getNumberOfItemsListedInShoppingCartIcon(), is("1"));
            topBanner.selectShoppingCartIcon();
            shoppingBasketPage = new ShoppingBasketPage(driver);
            shoppingBasketPage.verifyOnShoppingBasketPage();
            assertThat("The item in the basket did not match that expected", shoppingBasketPage.getHeaderOfItemInBasket(), is(new LoadProduct().getBookTitle("book1")));
        }

        @Test
        public void canAddMultipleItemsToBasket() {
            productPage.addItemToBasket("book1");
            productPage.addItemToBasket("book2");
            assertThat("The number of items in the basket did not match that expected", topBanner.getNumberOfItemsListedInShoppingCartIcon(), is("2"));
            topBanner.selectShoppingCartIcon();
            shoppingBasketPage = new ShoppingBasketPage(driver);
            shoppingBasketPage.verifyOnShoppingBasketPage();
            ArrayList<String> basketItems = shoppingBasketPage.getHeadersOfAllItemsInBasket();
            assertThat("The items in the Basket did not match those expected", basketItems, hasItems(new LoadProduct().getBookTitle("book1"),
                    new LoadProduct().getBookTitle("book2")));
        }

        @AfterEach
        public void tearDown() {
            DriverManager.quitDriver();
        }
    }

}
