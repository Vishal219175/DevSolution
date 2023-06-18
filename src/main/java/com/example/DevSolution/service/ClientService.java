package com.example.DevSolution.service;

import com.example.DevSolution.entity.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private List<Client> clients;

    public ClientService() {
        this.clients = new ArrayList<>();
    }

    public ClientService(List<Client> clients) {
        this.clients = clients;
    }

    /**
     * Creates a new client.
     *
     * @param client The client object to be created.
     * @return The created client.
     * @throws IllegalArgumentException if the ID number or mobile number is invalid or already exists.
     */

    public Client createClient(Client client) {
        // Perform validation for ID number and mobile number
        if (!isSouthAfricanIdNumberValid(client.getIdNumber())) {
            throw new IllegalArgumentException("Invalid South African ID Number");
        }

        if (isDuplicateIdNumber(client.getIdNumber())) {
            throw new IllegalArgumentException("Duplicate ID Number");
        }

        if (isDuplicateMobileNumber(client.getMobileNumber())) {
            throw new IllegalArgumentException("Duplicate Mobile Number");
        }

        clients.add(client);
        return client;
    }


    /**
     * Retrieves a client by ID.
     *
     * @param idNumber The ID number of the client.
     * @return The client with the specified ID, or null if not found.
     */

    public Client getClientById(String idNumber) {
        return clients.stream()
                .filter(c -> c.getIdNumber().equals(idNumber))
                .findFirst()
                .orElse(null);
    }


    /**
     * Updates a client.
     *
     * @param idNumber      The ID number of the client to update.
     * @param updatedClient The updated client object.
     * @return The updated client.
     * @throws IllegalArgumentException if the ID number or mobile number is invalid or already exists,
     *                                  or if the client with the specified ID is not found.
     */
    public Client updateClient(String idNumber, Client updatedClient) {
        Client client = getClientById(idNumber);
        if (client != null) {

            if (!isSouthAfricanIdNumberValid(updatedClient.getIdNumber())) {
                throw new IllegalArgumentException("Invalid South African ID Number");
            }

            if (client.getIdNumber().equals(updatedClient.getIdNumber()) && isDuplicateIdNumber(updatedClient.getIdNumber())) {
                throw new IllegalArgumentException("Duplicate ID Number");
            }

            if (client.getMobileNumber().equals(updatedClient.getMobileNumber()) && isDuplicateMobileNumber(updatedClient.getMobileNumber())) {
                throw new IllegalArgumentException("Duplicate Mobile Number");
            }

            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setMobileNumber(updatedClient.getMobileNumber());
            client.setIdNumber(updatedClient.getIdNumber());
            client.setPhysicalAddress(updatedClient.getPhysicalAddress());

            return client;
        } else {
            throw new IllegalArgumentException("Client not found");
        }
    }

    /**
     Searches for clients based on the provided parameters.
     @param firstName The first name of the client.
     @param idNumber The ID number of the client.
     @param mobileNumber The mobile number of the client.
     @return The client matching the search criteria.
     @throws IllegalArgumentException If no search criteria are provided or if a client with the specified first name, ID number, or mobile number is not found.
     */

    public Client searchClient(String firstName, String idNumber, String mobileNumber) {
        if (firstName != null) {
            return clients.stream()
                    .filter(c -> c.getFirstName().equals(firstName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Client with the given first name not found"));
        }

        if (idNumber != null) {
            return clients.stream()
                    .filter(c -> c.getIdNumber().equals(idNumber))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Client with the given ID number not found"));
        }

        if (mobileNumber != null) {
            return clients.stream()
                    .filter(c -> c.getMobileNumber().equals(mobileNumber))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Client with the given mobile number not found"));
        }

        throw new IllegalArgumentException("No search criteria provided");
    }

    /**
     * Checks if a South African ID number is valid.
     *
     * @param idNumber The ID number to validate.
     * @return true if the ID number is valid, false otherwise.
     */
    private boolean isSouthAfricanIdNumberValid(String idNumber) {

        if (idNumber.length() != 13 || !idNumber.matches("\\d{13}")) {
            return false;
        }
        return true;
    }

    /**
     * Checks if an ID number already exists for any client.
     *
     * @param idNumber The ID number to check.
     * @return true if the ID number is a duplicate, false otherwise.
     */
    private boolean isDuplicateIdNumber(String idNumber) {
        return clients.stream()
                .anyMatch(c -> c.getIdNumber().equals(idNumber));
    }

    /**
     * Checks if a mobile number already exists for any client.
     *
     * @param mobileNumber The mobile number to check.
     * @return true if the mobile number is a duplicate, false otherwise.
     */
    private boolean isDuplicateMobileNumber(String mobileNumber) {
        return clients.stream()
                .anyMatch(c -> c.getMobileNumber().equals(mobileNumber));
    }
}
