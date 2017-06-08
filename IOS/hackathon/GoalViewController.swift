//
//  GoalViewController.swift
//  Nessie-iOS-Wrapper
//
//  Created by John Madden on 6/7/17.
//  Copyright © 2017 Nessie. All rights reserved.
//

import UIKit
import NessieFmwk

class GoalViewController: UIViewController {
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var amtLabel: UILabel!
    @IBOutlet weak var goalLabel: UILabel!
    @IBOutlet weak var eventLabel: UILabel!

    override func viewDidLoad() {
        super.viewDidLoad()
        
        //testPostCustomer()
        
        /*
        AccountRequest().getAccounts(nil, completion:{(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                if let array = response as Array<Account>? {
                    if array.count > 0 {
                        let account = array[0] as Account?
                        self.testGetAccount(accountId: account!.accountId)
                        print(array)
                    } else {
                        print("No accounts found")
                    }
                }
            }
        })
        */
        // Do any additional setup after loading the view.
    }
    
    func testGetCustomers() {
        CustomerRequest().getCustomers({(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                if let array = response as Array<Customer>? {
                    if array.count > 0 {
                        let customer = array[0] as Customer?
                        self.testGetCustomer(customerId: customer!.customerId)
                        print(array)
                    } else {
                        print("No accounts found")
                    }
                }
            }
        })
    }
    
    func testGetCustomer(customerId: String) {
        CustomerRequest().getCustomer(customerId, completion:{(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                if let customer = response as Customer? {
                    print(customer)
                    self.testGetCustomers(from: "57d20f881fd43e204dd48418")
                }
            }
        })
    }
    
    func testGetCustomers(from accountId: String) {
        CustomerRequest().getCustomer(accountId, completion:{(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                if let customer = response as Customer? {
                    print(customer)
                    self.testPostCustomer()
                }
            }
        })
    }
    
    // Create Customer
    func testPostCustomer() {
        let address = Address(streetName: "Street", streetNumber: "1", city: "City", state: "VA", zipCode: "12345")
        let customerToCreate = Customer(firstName: "Victor", lastName: "Lopez", address: address, customerId: "asd")
        CustomerRequest().postCustomer(customerToCreate, completion:{(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                let customerResponse = response as BaseResponse<Customer>?
                let message = customerResponse?.message
                let customerCreated = customerResponse?.object
                print("\(message): \(customerCreated)")
                self.testPutCustomer(customerToBeModified: customerCreated!)
            }
        })
    }
    
    func testPutCustomer(customerToBeModified: Customer) {
        customerToBeModified.firstName = "Raul"
        CustomerRequest().putCustomer(customerToBeModified, completion:{(response, error) in
            if (error != nil) {
                print(error!)
            } else {
                let accountResponse = response as BaseResponse<Customer>?
                let message = accountResponse?.message
                let accountCreated = accountResponse?.object
                print("\(message): \(accountCreated)")
            }
        })
    }
 
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
