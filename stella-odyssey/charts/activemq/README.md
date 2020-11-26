HELM 3 adapter

Ready to work

1. First, install NFS network file storage and set the directory of data permanent storage as PV Shared volume;

2. Modify the IP address, directory path, storage size and storage class name in the Stella-Activemq-PV-PVC.YAML file according to the actual environment;

3. Execute the command Kubectl Apply-f stella-activemq-pv-pvc.yam in the default namespace

4. Execute the command Kubectl GET PV and Kubectl get PVC to check the binding situation


The modified values.yaml file can generate the corresponding configuration according to the actual situation, and the value of the file will be mapped to the applied yaml file

Start the installation

1. The tar xf activemq-v1.1.1.tgz

2. helm install -f values.yaml helm-activemq .

3. helm list

4. kubectl get pod

5. kubectl get service
	
