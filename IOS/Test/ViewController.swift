//
//  ViewController.swift
//  test
//
//  Created by John Madden on 6/7/17.
//  Copyright © 2017 Nessie. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet var test: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.test.image = UIImage(named: "logo.png")
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

