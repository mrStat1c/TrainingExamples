import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Timestamp;

public class Main {


    public static void main(String[] args) throws JAXBException {

        Transaction txn = new Transaction();
        txn.setId(345745);
        txn.setDateTime(new Timestamp(System.currentTimeMillis()).toString());
        txn.setOrderSum(2543);
        Transaction.Products products = new Transaction.Products();
        products.getProducts().add(new Transaction.Product("PROD_23454"));
        products.getProducts().add(new Transaction.Product("PROD_95435"));
        products.getProducts().add(new Transaction.Product("PROD_77003"));
        txn.setProducts(products);
        txn.setSalePoint("Super Shop");
        txn.setAlcohol(false);
        txn.setAddress("M Street, 48");
        txn.setPhone("+7(999)444-55-66");

        File file = new File("C:\\123\\transactions.xml");
        JAXBContext context = JAXBContext.newInstance(Transaction.class);

//      marshalling (Object to XML-file)
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(txn, file);

//      unmarshalling (XML-file to Object)
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Transaction unmarshalledTxn = (Transaction) unmarshaller.unmarshal(file);

        System.out.println("Unmarshalled txn:");
        System.out.println("id = " + unmarshalledTxn.getId());
        System.out.println("dateTime = " + unmarshalledTxn.getDateTime());
        System.out.println("orderSum = " + unmarshalledTxn.getOrderSum());
        System.out.println("product1 = " + unmarshalledTxn.getProducts().getProducts().get(0).getName());
        System.out.println("product2 = " + unmarshalledTxn.getProducts().getProducts().get(1).getName());
        System.out.println("product3 = " + unmarshalledTxn.getProducts().getProducts().get(2).getName());
        System.out.println("salePoint = " + unmarshalledTxn.getSalePoint());
        System.out.println("alcohol = " + unmarshalledTxn.isAlcohol());
        System.out.println("address = " + unmarshalledTxn.getAddress());
        System.out.println("phone = " + unmarshalledTxn.getPhone());
    }
}
