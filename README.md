# README

## How to run
    javac *.java
    java VisualBlockchain

## Introduction

* What is VisualBlockchain

    VisualBlockchain is a visualization tool that simulate how is blockchain formed. It simulate the process of generating transaction pool, mining and publishing new block to the current blockchain. And it shows the information in a block and the current status of each cryptocurrency to help you understand how the mining process works.

* How it works

    VisualBlockchain is programmed in Java with Swing GUI toolkit. The rule of the blockchain is based on project 1 "StringCoin" and the mining process is based on project 2 "Mining". And I made some changes of StringCoin to make this cryptocurrecy more reasonable, and let's call it VisualCoin.

    VisualCoin is based on StringCoin, which means that every coin is represented by a string like "0000" which is also the only identification of the cryptocurrency. The puzzle problem of VisualCoin is exactly the same as the puzzle in Mining (finding a Nonce that make the hash of the block is smaller than a target value), and the difficulty is set to 100000. And each transaction can only transfer one VisualCoin.

    Each time when a miner solves the puzzle, the block will be immediately added to the current blockchain. And the miner will get reward from the "coinbase" (a fixed public key), and the reward will always be one VisualCoin.

    In detail about the process, before starting mining, the miner will collect transactions in transaction pool, where the user could add transactions into it, then add a transaction from the "coinbase" as the reward and generate a candidate block. Then, by clicking "Start Mining", all the miners will start mining together in multithreads. When a miner finds the nonce, the system will check the correctness of the nonce. If the nonce is correct, the block will be the top block of the current blockchain, and the system will go through the entire blockchain to check the affiliation of each VisualCoin.

* How to use this tool

    run `java VisualBlockchain`

    And you could see the interface of the program.
    
    ![image](https://github.com/Zhuolun1996/CS1699_VisualBLockchain/blob/master/ReadmeImages/CS1699_VisualCoin_1.png)
    
    Click "New Transaction" you will see the dialog of "New Transaction".
    
    ![image](https://github.com/Zhuolun1996/CS1699_VisualBLockchain/blob/master/ReadmeImages/CS1699_VisualCoin_2.png)

    Click "New Miner" you will see the dialog of "New Miner".
    
    ![image](https://github.com/Zhuolun1996/CS1699_VisualBLockchain/blob/master/ReadmeImages/CS1699_VisualCoin_3.png)

    Click "Start with Initiation" and click "Start Mining" three times, you will see there are two new miners and three blocks are generated. Move the mouse to the miners or blocks you will see the information of the miner or block.
    
    ![image](https://github.com/Zhuolun1996/CS1699_VisualBLockchain/blob/master/ReadmeImages/CS1699_VisualCoin_4.png)


* Structure of the code

    Please read the comment in each file.

    There may be some unexpected bugs.