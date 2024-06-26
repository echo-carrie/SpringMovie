package example.springmovie.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AlipayConfig {
    public static String serverUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    public static String appId = "9021000137675468";
    public static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDGsrpm1b/eiBwt0C0vfXdC+Xn1SRmM+yYOXbT/eQ7g3dkUjsVFkGGGfv7YHRUWoVdnRYCNwB5Wm415KZ+udawMjRJ1LT8aekdt6B8W9/Qn9mKHXuH3zyNFATMhsPpMP2zx1jJkx4XWo+CWq02FZZw9vjgXVigKiSIhxFhx348mkwD4X1sBVmUvhuGrXFB4Swk4rWLTDbZc2OC5luo1JriKgTw8JADxx3ctzp8i8IfJNtfaf+XMCtRLX3lF082szsHqVGG09NxMI1i2o8SwjKQVFQ+idAl+Xtixhn7FRILVmDPD8VkKP5DTGNBL6wLnZ1h6e8PHDERz5raiBN/HBKTxAgMBAAECggEAZICwsiU56JhWEII06MmAknrXyk7QQrQiGrAhRft4oBr2m2RfugD+o2YHB08mhIzVReIs1QYxEkOZ+ogoQbk5bhvJE0XwnjbtfFUlrJt8ft4sFibTC2JVh6jEsSzwXbLTKzvZv4oAU4IOroc1Ld0oez6E5qDonfOhNQ04Y1jd8ncQ+IHmgVowRLG8ptE8l+T1qGqMmjBnghA1nOJR5Y/I3E+4YeUqxBnFsJ+pqZjRXfVSfLKoT62w2Zeq/SjLvVWASwesotiSM2xo+6O6C/hhpXUgIRwpDw/EoASkXYi3Ndfz3OqIBWklTrORPzwGg84W1pJb+SzwPOfW1P0yP+HuVQKBgQD+jl3zE5qy7/nQ6CnyxwYMIobgKbEwe8fwKQe8p6dNv9XvG/xNgKDyvcu62jZ8CzJHkxo2Q2aVqDcPhuAUXLQ7uQiEICwoPzvLIjE77murKYNkP56YhYTGbKO0MTxs/bC56s+8ASee+oHNSmy2edMg3B0D/IyYKyYjyj+YYU01XwKBgQDH00Bka6tzXPd/RSfCcR7TkvaVSHTzxQjxBQSqSHeHbT46Zqi+BSD5WzQfLOnfdEqi0DA6oe71MTSQajJu23c5A/TjJS57ArK2Dd+ugGPKyzCBbUHV/bfug70rylJTX6Mh3Uh3gucJ+w6q8gHZXGwTz6G5WJzWpPnotIoXGVJ3rwKBgGqS1EammIf97ItIB/f6109RTUKgceyohibIJtMXJfZpMHoxL0qLZdb7G0aZgWIhleVdcwXw5gwWJyc7AqnDvjma2DPo/FPFVZVQNWiwAyff84CPGiklDVe9uDQCwReohsrvwhUmLp5ipuF6HVWwX3JMdWFr5Lj4p7koPcPgSODlAoGAJ4yVIwr4XRXaWlMqol5XNpLQwQ0dMJ4fvew7VySOm7wwY8irvygutYuqjj0KcNKIbx77XK9uJxM9DxQltTE0sJEq58YVI/2Nt2CZk+UPWzTJ/ePM8viLbeafC0q47o1wMSSl0hmTdrM83PKXERksvuMKZC+4ovb2C2uoJRg67TsCgYAnJYbvL925XgtDI/dasFHtu288lHDkHg37zUc1aoOCzIzAdOnQ8A85grJKkrsr/iyEId+BhN99r3z7KEWs8zZBbInVPF9DwJAkxaqFJd+HhsWvwiyhBe6bN3GT/DlfZTmqQJFaLMjfWVnbjQi+6W0cBNMLK7C5RauFO8tdasZAZQ==";
    public static String format = "json";
    public static String charset = "utf-8";
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnafEL825ABw7mEZD+uxfjdrC7batgNvZM+z2p3XC6Y1ff485EHaoD1fq1pry4mFzZklh+PyNQca9PZf5Dnrh1O9RkyVV2qDXVTBsqBSGsZ0Jn8zfg/MfU6l5C4lO0pI3w3WrsVJYirikRz5Vq7VzCdxzHzu3NmcpneV/CmUpDlemPp5Q/kfzmxlJ5YVbL7bdBeu+RDd5mU8KLFI9AmLI6khFU9yOd8RKlTo9Hnt7jG0Zwd0+PM0h60lr6Xp9gUCltBFKvZwxMLAtIFp+E8dBmfy2/yTVpGaefqTf9oIv/zdSEzHAZHLD8+B/DsKimNxpt2Wm4AHZgM0H4UGf97HQfQIDAQAB";
    public static String signType = "RSA2";
    public static AlipayClient getAlipayClient() {
        return new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publicKey, signType);
    }
}
