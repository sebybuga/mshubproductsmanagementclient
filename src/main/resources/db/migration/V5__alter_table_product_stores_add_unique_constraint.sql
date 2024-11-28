USE `hubproductsmanagement`;

ALTER TABLE product_stores
ADD CONSTRAINT UK_PRODUCT_STORES UNIQUE (productId,storeId);