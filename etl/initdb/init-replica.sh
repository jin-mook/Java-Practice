# initdb/init-replica.sh
#!/bin/bash
echo "Initializing replica set"
mongosh --eval 'rs.initiate({_id: "rs0", members: [{_id: 0, host: "mongo:27017"}]})'
