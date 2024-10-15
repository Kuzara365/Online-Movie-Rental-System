/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author votha
 */


public class Customer {
    private String customerId;
    private String username;
    private String password;
    private String subscriptionType;

    public Customer(String customerId, String username, String password) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
        this.subscriptionType = "Free";
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public void subscribeToFree() {
        this.subscriptionType = "Free";
    }

    public void subscribeToPremium() {
        this.subscriptionType = "Premium";
    }

    @Override
    public String toString()  {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", subscriptionType:'" + subscriptionType + '\'';
    }
}