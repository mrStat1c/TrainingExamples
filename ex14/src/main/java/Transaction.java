import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "txn")
public class Transaction {

    @XmlAttribute(required = true, name = "id")
    private long id;
    @XmlElement(required = true, name = "dateTime")
    @XmlSchemaType(name = "dateTime")
    private String dateTime;
    @XmlElement(required = true, name = "salePoint")
    private String salePoint;
    @XmlElement(required = true, name = "orderSum")
    private int orderSum;
    @XmlElement(required = true, name = "products")
    private Products products;
    @XmlElement(required = true, name = "alcohol")
    private boolean alcohol;
    @XmlElement(name = "address")
    private String address;
    @XmlElement(name = "phone")
    private String phone;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Products{
        @XmlElement(required = true, name = "product")
        private List<Product> products = new ArrayList<>();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Product{
        @Getter
        @XmlAttribute(required = true, name = "name")
        private String name;
    }
}
